import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

interface CadastroPageProps {}

const CadastroPage: React.FC<CadastroPageProps> = () => {
  const [nome, setNome] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleNomeChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setNome(event.target.value);
  };

  const handleEmailChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setEmail(event.target.value);
  };

  const handlePasswordChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setPassword(event.target.value);
  };

  const handleCadastro = async () => {
    try {
      await axios.post('/api/cadastro', { nome, email, password });
      navigate('/login');
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4 justify-items-center">
      <div className="bg-white p-10 rounded-md shadow-md mx-auto">
        <h1 className="text-3xl font-bold text-center mb-5">Cadastro</h1>
        <input
          type="text"
          placeholder="Nome"
          className="w-full border border-gray-300 p-2 rounded-md mb-3"
          value={nome}
          onChange={handleNomeChange}
        />
        <input
          type="email"
          placeholder="Email"
          className="w-full border border-gray-300 p-2 rounded-md mb-3"
          value={email}
          onChange={handleEmailChange}
        />
        <input
          type="password"
          placeholder="Password"
          className="w-full border border-gray-300 p-2 rounded-md mb-3"
          value={password}
          onChange={handlePasswordChange}
        />
        <button
          className="bg-blue-500 text-white p-2 rounded-md w-full"
          onClick={handleCadastro}
        >
          Cadastrar
        </button>
      </div>
    </div>
  );
};

export default CadastroPage;
