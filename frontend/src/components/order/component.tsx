import { useNavigate, useParams } from '@solidjs/router'
import {
    Component,
    For,
    InitializedResourceReturn,
    Show,
    createEffect,
    createSignal,
} from 'solid-js'
import style from './style.module.css'
import * as menuItems from '#components/menu-items/store'
import { MenuItem, OrderContent } from '#types/models'

import { fetchApi } from '#fetchers/generic'
import { createResource } from 'solid-js'
import * as orders from '../orders/store'
import { createOrder, updateOrder } from '#fetchers/order'
import { updateOrderContent } from '#fetchers/order-content'

type MenuItemWithQuantity = MenuItem & { quantity: number }

const MenuItemEntry: Component<
    MenuItemWithQuantity & {
        contents: InitializedResourceReturn<OrderContent[]>
    }
> = (props) => {
    return (
        <tr>
            <td>{props.name}</td>
            <td>{props.price}₴</td>
            <td>
                <input
                    value={props.quantity}
                    onchange={(event) => {
                        const newValue = Number(event.target.value)
                        const [records, { mutate }] = props.contents
                        const buffer = records()
                        const record = buffer.find(
                            (x) => x.menuItemId === props.id,
                        )
                        if (record) {
                            record.quantity = newValue
                        } else {
                            buffer.push({
                                orderId: 0,
                                menuItemId: props.id,
                                quantity: newValue,
                            })
                        }
                        mutate(buffer)
                    }}
                    type="number"
                    class={style.quantityInput}
                    min={0}
                />
            </td>
        </tr>
    )
}

export const OrderPage: Component = () => {
    const params = useParams<{ id: string }>()

    const isNew = params.id === 'new'
    let id = Number(params.id)

    const [clientName, setClientName] = createSignal(
        isNew ? '' : orders.data().find((x) => x.id === id)?.clientName ?? '',
    )

    const contentsResource = createResource<OrderContent[]>(
        () => (isNew ? [] : fetchApi<OrderContent[]>(`/orders/${id}/contents`)),
        { initialValue: [] },
    )
    const [contents] = contentsResource

    createEffect(() => {
        console.log(contents())
    })

    const menuItemsWithQuantities = (): MenuItemWithQuantity[] =>
        menuItems.data().map((x) => ({
            ...x,
            quantity:
                contents().find((y) => y.menuItemId === x.id)?.quantity ?? 0,
        }))

    const navigate = useNavigate()

    async function handleSave() {
        if (isNew) {
            id = await createOrder({ clientName: clientName(), paid: false })
        } else {
            await updateOrder({ id, clientName: clientName(), paid: false })
        }
        await updateOrderContent(id, contents())
        navigate('/')
        orders.refetch()
    }

    async function handleDelete() {
        await fetchApi(`/orders/${id}/contents`, { method: 'DELETE' })
        navigate('/', { replace: true })
        orders.refetch()
    }

    return (
        <div class={style.page}>
            <div class={style.box}>
                <h1 style="text-align: center">Order #{params.id}</h1>

                <div class={style.clientRow}>
                    <label for="name">Client name:</label>
                    <input
                        type="text"
                        id="name"
                        name="name"
                        value={clientName()}
                        onChange={(event) => setClientName(event.target.value)}
                    />
                </div>

                <table class={style.table}>
                    <thead>
                        <tr>
                            <td>Name</td>
                            <td>Price</td>
                            <td>Quantity</td>
                        </tr>
                    </thead>
                    <tbody>
                        <For each={menuItemsWithQuantities()}>
                            {(menuItem) => (
                                <MenuItemEntry
                                    {...menuItem}
                                    contents={contentsResource}
                                />
                            )}
                        </For>
                    </tbody>
                </table>
                <button onclick={handleSave}>Save</button>
                <Show when={!isNew}>
                    <button onclick={handleDelete}>Delete</button>
                </Show>
            </div>
        </div>
    )
}
