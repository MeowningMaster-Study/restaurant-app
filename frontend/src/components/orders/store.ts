import { fetchApi } from '#fetchers/generic'
import { Order } from '#types/models'
import { createResource } from 'solid-js'

export const [data, { mutate, refetch }] = createResource<Order[]>(
    () => fetchApi('/orders'),
    { initialValue: [] },
)
