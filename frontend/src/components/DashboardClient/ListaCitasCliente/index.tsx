import { Form, InputGroup, Table } from "react-bootstrap"


const ListaCitasCliente = () => {
  return (
    <div>
           <div>
      <h1 className="text-center">Lista de citas</h1>

      <div className="mb-3">
        <Form.Label htmlFor="basic-url">Buscar turno</Form.Label>
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
            <th className="text-center">Horario</th>
            <th className="text-center">Detalles</th>
            <th className="text-center">Videollamada</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td className="text-center">1</td>
            <td className="text-center">Mark</td>
            <td className="text-center">123456</td>
            <td className="text-center">pediatría</td>
            <td className="text-center">09:00 - 10:00 </td>
            <td className="text-center"> Detalles de la sesion</td>
            <td className="text-center">
              <button className="btn-navbar-pct">
                Iniciar Videollamada
              </button>
            </td>
          </tr>
        </tbody>
      </Table>
    </div>
    </div>
  )
}

export default ListaCitasCliente