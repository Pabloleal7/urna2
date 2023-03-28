import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import LoginPage from '../src/components/LoginPage';
import CadastroPage from '../src/components/CadastroPage';
import Turmas from '../src/components/TurmasPage';
import ChapasPage from '../src/components/ChapasPage';


const MyRoutes = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<LoginPage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/cadastro" element={<CadastroPage />} />
        <Route path="/cadastro2" element={<CadastroPage />} />
        <Route path="/turmas" element={<Turmas />} />
        <Route path="/turma/:id/chapas" element={<ChapasPage/>} />

      </Routes>
    </Router>
  );
};

export default MyRoutes;
