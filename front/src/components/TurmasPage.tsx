import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Turma } from './Turma';
import { useNavigate } from 'react-router-dom';
import { Button, Container } from 'react-bootstrap';

const TurmasPage = () => {
  const [turmas, setTurmas] = useState<Turma[]>([]);
  const navigate = useNavigate();

  useEffect(() => {
    const token = localStorage.getItem('token');

    axios
      .get<Turma[]>('http://apiurna-env.eba-m58zxm2n.us-east-1.elasticbeanstalk.com/turmas', {
        headers: {
          Authorization: `Bearer ${token}`
        }
      })
      .then(response => {
        setTurmas(response.data);
      })
      .catch(error => {
        console.log(error);
      });
  }, []);

  const handleTurmaClick = (id: string) => {
    navigate(`/turma/${id}/chapas`);
  };

  return (
    <Container className="h-100vh d-flex justify-content-center align-items-center">
      <div>
        <h1 className="text-center mb-4">Turmas</h1>
        {turmas.map(turma => (
          <Button
            key={turma.id}
            variant="outline-primary"
            className="w-100 mb-3"
            onClick={() => handleTurmaClick(turma.id)}
          >
            {turma.nomeDaTurma} - {turma.sala} - {turma.turno}
          </Button>
        ))}
      </div>
    </Container>
  );
};

export default TurmasPage;
