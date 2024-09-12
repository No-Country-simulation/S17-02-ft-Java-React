import Table from 'react-bootstrap/Table';

const ListaCitas = () => {
  return (
    <div className="container p-3">
      <h1 className="text-center mb-4">Listado de Citas</h1>
      <div className="mb-3">
        <div className="mb-3 fs-5 gap-3">
          <label  className="form-label">Buscar</label>
          <input
            type="text"
            name=""
            size={50}
            className="form-control"
            placeholder=""
            aria-describedby="helpId"
          />
          <small id="helpId" className="text-muted">buscador de turnos</small>
        </div>
        
      </div>
       <Table striped="columns" className='fs-5'>
      <thead>
        <tr>
          <th className='text-center'>#</th>
          <th className='text-center'>Paciente</th>
          <th className='text-center'>fecha</th>
          <th className='text-center'>horario</th>
          <th className='text-center'>detalles</th>
          <th className='text-center'>Videollamada</th>
          <th className='text-center'>Historial clínico</th>
        </tr>
      </thead>
      <tbody>
        <tr className=''>
          <td className='text-center fs-5'>1</td>
          <td className='text-center fs-5'>Mark</td>
          <td className='text-center fs-5'>10/10/2024</td>
          <td className='text-center fs-5'>10:00</td>
          <td className='text-center fs-5'>detalles de la sesión</td>
          <td className='text-center fs-5'><button className="btn btn-primary fs-5">Iniciar Videollamada</button></td>
          <td className='text-center fs-5'><button className="btn btn-primary fs-5">ver Historial clínico</button></td>
        </tr>
      </tbody>
    </Table>
    </div>
  )
}

export default ListaCitas