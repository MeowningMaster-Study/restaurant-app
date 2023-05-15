import urlJoin from 'url-join'

const backendHost = import.meta.env.VITE_BACKEND_HOST

type Options = { method?: 'GET' | 'POST' | 'PUT' | 'DELETE'; body?: unknown }

export async function fetchApi<Reply = undefined>(
    path: string,
    options: Options = {},
) {
    const url = urlJoin(backendHost, path)

    const headers: Record<string, string> = { accept: 'application/json' }
    if (options.body) {
        headers['content-type'] = 'application/json'
    }

    const reply = await fetch(url, {
        method: options.method,
        headers,
        body: options.body && JSON.stringify(options.body),
    })
    const text = await reply.text()
    if (!reply.ok) {
        throw new Error(text)
    }
    return (text !== '' && JSON.parse(text)) as Reply
}
