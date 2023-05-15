export type MenuItem = {
    id: number
    name: string
    price: number
}

export type Order = {
    id: number
    clientName: string
    paid: boolean
}

export type OrderContent = {
    orderId: number
    menuItemId: number
    quantity: number
}
