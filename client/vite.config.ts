import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import path from 'path'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  resolve: {
     	alias: { //cria um alias para facilitar os imports no projeto
       "@": path.resolve(__dirname, "src"), //@ será um atalho que representa o diretório src
   //Em vez de escrever algo como: import Component from '../../components/Component'
   //Pode ser usado: import Component from '@/components/Component'
    }
    }
})

