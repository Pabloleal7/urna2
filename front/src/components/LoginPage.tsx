import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { Button, Col, Container, Form, Row } from 'react-bootstrap';

interface LoginPageProps {}

const LoginPage: React.FC<LoginPageProps> = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleUsernameChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setUsername(event.target.value);
  };

  const handlePasswordChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setPassword(event.target.value);
  };

  const handleKeyDown = (event: React.KeyboardEvent<HTMLFormElement>) => {
    if (event.key === 'Enter') {
      handleLogin();
    }
  };

  const handleLogin = async () => {
    try {
      const response = await axios.post('http://152.67.50.27/api/v1/login', { username, password });
      localStorage.setItem('token', response.data.token);
      localStorage.setItem('refreshToken', response.data.refreshToken);
      navigate('/turmas');
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <Container className="h-100vh d-flex justify-content-center align-items-center mt-5">
      <div className="bg-white p-4 rounded shadow-lg" style={{ maxWidth: '400px', width: '100%' }}>
        <div className="text-center mb-4">
          <h1>Login</h1>
        </div>
        <Form onKeyDown={handleKeyDown}>
          <Form.Group controlId="formBasicEmail">
            <Form.Control
              type="email"
              placeholder="Email"
              value={username}
              onChange={handleUsernameChange}
            />
          </Form.Group>
          <Form.Group controlId="formBasicPassword">
            <Form.Control
              type="password"
              placeholder="Password"
              value={password}
              onChange={handlePasswordChange}
            />
          </Form.Group>
          <Button variant="danger" className="w-100 mt-3" onClick={handleLogin}>
            Login
          </Button>
        </Form>
      </div>
    </Container>
  );
};

export default LoginPage;
