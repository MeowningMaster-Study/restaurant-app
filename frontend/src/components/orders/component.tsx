import { Component, For, Show } from 'solid-js'
import * as orders from './store'
import style from './style.module.css'
import { A, useNavigate } from '@solidjs/router'
import { Order } from '#types/models'
import { auth0 } from '#auth/auth0-client'
import { authenticated, setAuthenticated } from '#auth/signal'

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

    async function initAuth() {
        await auth0.loginWithRedirect()
    }

    async function logout() {
        await auth0.logout()
        setAuthenticated(false)
    }

    return (
        <div class={style.page}>
            <div class={style.box}>
                <Show
                    when={authenticated()}
                    fallback={
                        <>
                            You are not authorized
                            <button onclick={initAuth}>Login</button>
                        </>
                    }
                >
                    <h1 style="text-align: center">Orders 🧾</h1>
                    <ul>
                        <For each={orders.data()}>
                            {(order) => <OrderEntry {...order} />}
                        </For>
                    </ul>
                    <button onclick={() => navigate('/orders/new')}>
                        Create order +
                    </button>
                    <br />
                    <p>
                        You are authorized
                        <button onclick={logout}>Logout</button>
                    </p>
                </Show>
            </div>
        </div>
    )
}
