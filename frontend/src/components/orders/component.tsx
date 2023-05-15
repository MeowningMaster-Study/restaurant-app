import { Component, For } from 'solid-js'
import * as orders from './store'
import style from './style.module.css'
import { A, useNavigate } from '@solidjs/router'
import { Order } from '#types/models'

const OrderEntry: Component<Order> = (order) => {
    return (
        <li class={style.order}>
            <A href={`/orders/${order.id}`}>
                #{order.id}. {order.clientName} {order.paid && '✔'}
            </A>
        </li>
    )
}

export const OrdersPage: Component = () => {
    const navigate = useNavigate()

    return (
        <div class={style.page}>
            <div class={style.box}>
                <h1 style="text-align: center">Orders 🧾</h1>
                <ul>
                    <For each={orders.data()}>
                        {(order) => <OrderEntry {...order} />}
                    </For>
                </ul>
                <button onclick={() => navigate('/orders/new')}>
                    Create order +
                </button>
            </div>
        </div>
    )
}
