import React, { useState, useEffect } from "react";
import axios from "axios";
import { Chapa } from "./Chapa";
import { useParams } from "react-router-dom";
import { Button, Col, Container, Modal, Row } from "react-bootstrap";
import { Turma } from "./Turma";
import MenuSuperior from "./MenuSuperior";

const ChapasPage = () => {
  const [chapalist, setChapaList] = useState<Chapa[]>([]);
  const [turma, setTurma] = useState<Turma>();

  const [isModalOpen, setIsModalOpen] = useState(false);
  const [selectedChapaId, setSelectedChapaId] = useState("");
  const [timeLeft, setTimeLeft] = useState(0);
  const { id } = useParams<{ id: string }>();

  useEffect(() => {
    const token = localStorage.getItem("token");
    axios
      .get<Chapa[]>(`http://152.67.50.27/chapas/turma/${id}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        setChapaList(response.data);
      });

      axios
      .get<Turma>(`http://152.67.50.27/turmas/${id}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        setTurma(response.data);
      });
  }, []);

  useEffect(() => {
    let timer: ReturnType<typeof setTimeout>;
    if (timeLeft > 0) {
      timer = setTimeout(() => {
        setTimeLeft(timeLeft - 1);
      }, 1000);
    }
    return () => {
      clearTimeout(timer);
    };
  }, [timeLeft]);

  const handleVote = (id: string) => {
    if (timeLeft > 0) {
      alert(`Aguarde ${timeLeft} segundos antes de votar novamente.`);
      return;
    }
    setSelectedChapaId(id);
    setIsModalOpen(true);
  };

  const handleConfirmVote = () => {
    const token = localStorage.getItem("token");
    axios
      .post(`http://152.67.50.27/votos/${selectedChapaId}`, null, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then(() => {
        alert("Voto registrado com sucesso!");
        setTimeLeft(1);
      });
    setIsModalOpen(false);
  };

  const handleModalClose = () => {
    setIsModalOpen(false);
  };

  return (<>
    <MenuSuperior></MenuSuperior>
    <Container className="h-100vh d-flex justify-content-center align-items-center">
      
      <div>
        <h1>CHAPAS DA TURMA: {turma?.nomeDaTurma}  - SALA {turma?.sala}  - TURNO {turma?.turno}</h1>
        <Row>
          {chapalist.map((chapa, index) => (
            <Col key={chapa.id} md={4} className="mb-4">
              <div className="p-4 border rounded-lg shadow-md">
                <h2 className="text-lg font-semibold">{chapa.lider}</h2>
                <p className="text-sm text-gray-500">{chapa.viceLider}</p>
                <Button
                  onClick={() => handleVote(chapa.id)}
                  className="mt-4 px-4 py-2 bg-blue-500 text-white rounded-md shadow-md"
                  disabled={timeLeft > 0}
                >
                  Votar
                </Button>
              </div>
            </Col>
          ))}
        </Row>
        <Modal show={isModalOpen} onHide={handleModalClose}>
          <Modal.Header closeButton>
            <Modal.Title className="text-center">
              Confirmação de voto
            </Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <p>Você confirma o voto na chapa selecionada?</p>
          </Modal.Body>
          <Modal.Footer>
            <Button variant="primary" onClick={handleConfirmVote}>
              Confirmar
            </Button>
            <Button variant="secondary" onClick={handleModalClose}>
              Cancelar
            </Button>
          </Modal.Footer>
        </Modal>
      </div>
    </Container>
    </>
  );
};

export default ChapasPage;
