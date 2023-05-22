import { createAuth0Client } from '@auth0/auth0-spa-js'

export const auth0 = await createAuth0Client({
    domain: 'dev-t0gga85pfgnftt2h.us.auth0.com',
    clientId: 'dTBM5Haz6o0BXwQel2lViFXNmUkFYEae',
    authorizationParams: {
        redirect_uri: window.location.origin,
    },
})
