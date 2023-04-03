import { Nav, Navbar } from 'react-bootstrap';
import { NavLink, useLocation } from 'react-router-dom';

const MenuSuperior = () => {
  const location = useLocation();

  return (
    <Navbar bg="primary" expand="lg">
      <Navbar.Brand href="/" style={{ color: 'white', fontSize: '24px' }}>     CETEP 2023 - Votação para Líderes e Vice-líderes</Navbar.Brand>
      <Navbar.Toggle aria-controls="basic-navbar-nav" />
      <Navbar.Collapse id="basic-navbar-nav">
        <Nav className="mr-auto">
          <NavLink
            className={`btn btn${location.pathname === '/turmas' ? '' : '-outline'}-light`}
            to="/turmas"
            style={{ marginRight: '5px' }}
          >
            Turmas
          </NavLink>
          <NavLink
            className={`btn btn${location.pathname === '/resultado' ? '' : '-outline'}-light`}
            to="/resultado"
          >
            Resultados
          </NavLink>
        </Nav>
      </Navbar.Collapse>
    </Navbar>
  );
};

export default MenuSuperior;
