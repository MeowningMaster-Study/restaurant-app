import { OrderContent } from '#types/models'
import { fetchApi } from './generic'

export async function updateOrderContent(
    orderId: number,
    contents: Omit<OrderContent, 'orderId'>[],
) {
    return fetchApi(`/orders/${orderId}/contents`, {
        method: 'PUT',
        body: contents,
    })
}
