import { fetchApi } from '#fetchers/generic'
import { Order } from '#types/models'

export async function createOrder(order: Omit<Order, 'id'>) {
    return fetchApi<number>('/orders', { method: 'POST', body: order })
}

export async function updateOrder(order: Order) {
    return fetchApi<number>('/orders', { method: 'PUT', body: order })
}
