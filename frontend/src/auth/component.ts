import { ParentComponent } from 'solid-js'
import { auth0 } from './auth0-client'
import { setAuthenticated } from './signal'

export const Auth: ParentComponent = (props) => {
    async function checkAuth() {
        try {
            await auth0.getTokenSilently()
            setAuthenticated(true)
            return
        } catch (error) {
            console.log(error)
        }

        try {
            const redirectResult = await auth0.handleRedirectCallback()
            setAuthenticated(true)
            const user = await auth0.getUser()
            console.log(user)
            return
        } catch (error) {
            console.log(error)
        }
    }

    checkAuth()

    return props.children
}
