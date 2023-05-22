import * as path from 'node:path'
import { defineConfig } from 'vite'
import solidPlugin from 'vite-plugin-solid'

export default defineConfig({
    plugins: [solidPlugin()],
    server: {
        port: 3000,
    },
    build: {
        target: 'esnext',
    },
    resolve: {
        alias: Object.fromEntries(
            [
                'components',
                'styles',
                'utilities',
                'fetchers',
                'types',
                'auth',
            ].map((x) => [`#${x}`, path.resolve(`src/${x}`)]),
        ),
    },
})
