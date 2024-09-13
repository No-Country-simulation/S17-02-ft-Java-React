import { Form, InputGroup, Table } from "react-bootstrap";

const ListaEspecialist = () => {
  return (
    <div>
      <h1 className="text-center">Lista de especialistas</h1>

      <div className="mb-3">
        <Form.Label htmlFor="basic-url">buscar especialistas</Form.Label>
        <InputGroup className="mb-3">
          <InputGroup.Text id="basic-addon3">
           🔎
          </InputGroup.Text>
          <Form.Control id="basic-url" aria-describedby="basic-addon3" />
        </InputGroup>
      </div>

      <Table striped bordered hover>
        <thead>
          <tr>
            <th className="text-center">#</th>
            <th className="text-center">Nombre especialista</th>
            <th className="text-center">Matricula</th>
            <th className="text-center">especialidad</th>
            <th className="text-center">Horarios</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td className="text-center">1</td>
            <td className="text-center">Mark</td>
            <td className="text-center">123456</td>
            <td className="text-center">pediatría</td>
            <td className="text-center">
              <button className="btn btn-primary">
                Ver horarios disponibles
              </button>
            </td>
          </tr>
        </tbody>
      </Table>
    </div>
  );
};

export default ListaEspecialist;
