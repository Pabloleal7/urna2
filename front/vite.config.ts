import { defineConfig } from 'vite'
import reactRefresh from '@vitejs/plugin-react-refresh';

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [reactRefresh()],
  build: {
    rollupOptions: {
      output: {
        manualChunks: {},
      },
    },
  },
  server: {
    // Certifique-se de que o Vite está usando o histórico do navegador para rotas no lado do cliente
    historyApiFallback: true,
  },
})
