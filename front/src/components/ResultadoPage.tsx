import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Table, Container } from 'react-bootstrap';
import MenuSuperior from './MenuSuperior';


interface Chapa {
  id: string;
  lider: string;
  viceLider: string;
}

interface Voto {
  chapa: Chapa;
  totalVotos: number;
}

interface Turma {
  id: string;
  nomeDaTurma: string;
  sala: string;
  turno: string;
}

interface Resultado {
  turma: Turma;
  votos: Voto[];
}

const ResultadoPage = () => {
  const [resultados, setResultados] = useState<Resultado[]>([]);

  useEffect(() => {
    const token = localStorage.getItem("token");
    axios
      .get<Record<string, Voto[]>>(`http://apiurna-env.eba-m58zxm2n.us-east-1.elasticbeanstalk.com/votos`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        const data = response.data;
        const resultadosArray: Resultado[] = Object.keys(data).map((key) => {
          const turma: Turma = JSON.parse(key);
          const votos: Voto[] = data[key];
          return { turma, votos };
        });
        setResultados(resultadosArray);
      });
  }, []);

  return (
    <>
    <MenuSuperior></MenuSuperior>
      {resultados.map((resultado, index) => (
        <Container key={index} className="my-4">
          <h2>{resultado.turma.nomeDaTurma} - TURNO - {resultado.turma.turno} - SALA - {resultado.turma.sala}</h2>
          <Table striped bordered hover>
            <thead>
              <tr>
                <th>Líder</th>
                <th>Vice-Líder</th>
                <th>Total de Votos</th>
              </tr>
            </thead>
            <tbody>
              {resultado.votos.map((voto, idx) => (
                <tr key={idx}>
                  <td>{voto.chapa.lider}</td>
                  <td>{voto.chapa.viceLider}</td>
                  <td>{voto.totalVotos}</td>
                </tr>
              ))}
            </tbody>
          </Table>
        </Container>
      ))}
    </>
  );
};

export default ResultadoPage;