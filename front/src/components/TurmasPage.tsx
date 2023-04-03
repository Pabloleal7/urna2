import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Turma } from './Turma';
import { useNavigate } from 'react-router-dom';
import { Button, Container } from 'react-bootstrap';
import MenuSuperior from './MenuSuperior';

const TurmasPage = () => {
  const [turmas, setTurmas] = useState<Turma[]>([]);
  const navigate = useNavigate();

  useEffect(() => {
    const token = localStorage.getItem('token');

    axios
      .get<Turma[]>('http://152.67.50.27/turmas', {
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

  return (<>
    <MenuSuperior></MenuSuperior>
    <Container className="h-100vh d-flex justify-content-center ">
      <div>
        <h1 className="text-center mb-4">Matutino</h1>
        {turmas.filter(t=> t.turno == 'MATUTINO').map(turma => (
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
      <div className='mx-3'></div>
      <div>
        <h1 className="text-center mb-4">Vespertino</h1>
        {turmas.filter(t=> t.turno == 'VESPERTINO').map(turma => (
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
      <div className='mx-3'></div>
      <div>
        <h1 className="text-center mb-4">Noturno</h1>
        {turmas.filter(t=> t.turno == 'NOTURNO').map(turma => (
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
    </>
    
  );
};

export default TurmasPage;
