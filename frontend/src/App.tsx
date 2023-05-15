import { OrderPage } from '#components/order/component'
import { OrdersPage } from '#components/orders/component'
import { Route, Router, Routes } from '@solidjs/router'
import type { Component } from 'solid-js'
import '#styles/global/index.css'

export const App: Component = () => {
    return (
        <>
            <Router>
                <Routes>
                    <Route path="/" component={OrdersPage} />
                    <Route path="orders/:id" component={OrderPage} />
                </Routes>
            </Router>
        </>
    )
}
