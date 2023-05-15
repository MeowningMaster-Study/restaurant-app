import { fetchApi } from '#fetchers/generic'
import { MenuItem } from '#types/models'
import { createResource } from 'solid-js'

export const [data, { mutate, refetch }] = createResource<MenuItem[]>(
    () => fetchApi('/menu-items'),
    { initialValue: [] },
)
