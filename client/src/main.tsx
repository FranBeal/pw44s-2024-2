import { StrictMode } from "react"
import { createRoot } from "react-dom/client"
import './index.css'

import { BrowserRouter } from 'react-router-dom';
import App from './App.tsx';

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    {/*Avisa o React que agora o reac-router-dom está controlando as rotas*/}
    <BrowserRouter>
      {/*Fazer render do App onde estão as rotas base */}
      <App />
    </BrowserRouter>
  </StrictMode>,
)
