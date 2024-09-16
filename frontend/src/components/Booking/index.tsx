import React, { useState, useEffect } from "react";
import { useForm, Controller } from "react-hook-form";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import Modal from "react-bootstrap/Modal";
import "react-datepicker/dist/react-datepicker.css";
import DatePicker from "react-datepicker";
import { User } from "../../interfaces/profile";

interface BookingFormProps {
  user: User;
}

type FormData = {
  userName: string;
  userAge: number;
  userbirthDate: string;
  userGender: string;
  specialist: string;
  reason: string;
  appointmentDate: Date;
  monto: number;
};

const specialists = [
  { id: 1, name: "especialista 1", especialidad: "Cardióloga", monto: 1000 },
  { id: 2, name: "especialista 2", especialidad: "Pediatría", monto: 1000 },
  { id: 3, name: "especialista 3", especialidad: "Ginecóloga", monto: 1000 },
  { id: 4, name: "especialista 4", especialidad: "Neurología", monto: 1000 },
  { id: 5, name: "especialista 5", especialidad: "Dermatología", monto: 1000 },
];

const ReservationForm: React.FC<BookingFormProps> = ({ user }) => {
  const {
    register,
    handleSubmit,
    control,
    setValue,
    formState: { errors },
  } = useForm<FormData>();

  const [searchTerm, setSearchTerm] = useState("");
  const [filteredSpecialists, setFilteredSpecialists] = useState(specialists);
  const [showModal, setShowModal] = useState(false);
  const [submittedData, setSubmittedData] = useState<FormData | null>(null);

  useEffect(() => {
    if (user) {
      setValue("userName", user.name);
      setValue("userAge", user.age);
      setValue("userbirthDate", user.birthdate);
      setValue("userGender", user.gender);
    }
  }, [user, setValue]);

  useEffect(() => {
    setFilteredSpecialists(
      specialists.filter((specialist) =>
        `${specialist.name} - ${specialist.especialidad}`
          .toLowerCase()
          .includes(searchTerm.toLowerCase())
      )
    );
  }, [searchTerm]);

  const onSelectSpecialist = (
    specialistName: string,
    specialistMonto: number
  ) => {
    setValue("specialist", specialistName);
    setValue("monto", specialistMonto);
    setSearchTerm(specialistName);
    setFilteredSpecialists([]);
  };

  const onSubmit = (data: FormData) => {
    console.log("Datos del formulario:", data);
    setSubmittedData(data);
    setShowModal(true);
  };

  const handleConfirm = () => {
    console.log("Reserva confirmada:", submittedData);
    setShowModal(false);
  };

  return (
    <>
      <Form
        onSubmit={handleSubmit(onSubmit)}
        className="w-100 px-2 d-flex flex-column flex-md-row justify-content-center gap-5"
      >
        <fieldset className="w-50">
          <legend>Información del usuario</legend>

          <Form.Group className="mb-3">
            <Form.Label>Nombre:</Form.Label>
            <Form.Control type="text" {...register("userName")} disabled />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Edad:</Form.Label>
            <Form.Control type="number" {...register("userAge")} disabled />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Fecha de nacimiento:</Form.Label>
            <Form.Control
              className="hidden"
              type="text"
              {...register("userbirthDate")}
              disabled
            />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Género:</Form.Label>
            <Form.Control
              className="hidden"
              type="text"
              {...register("userGender")}
              disabled
            />
          </Form.Group>
        </fieldset>

        <fieldset className="w-50 d-flex flex-column">
          <legend className="mb-3">Búsqueda de especialistas</legend>

          <Form.Group className="mb-3" controlId="formBasicSpecialist">
            <Form.Label>Selecciona un especialista</Form.Label>
            <Form.Control
              type="text"
              value={searchTerm}
              onInput={(e) => setSearchTerm(e.currentTarget.value)}
              placeholder="Busca un especialista..."
              size="lg"
              {...register("specialist", {
                required: "Selecciona un especialista",
              })}
            />
            {errors.specialist && (
              <p className="text-danger">{errors.specialist.message}</p>
            )}

            {searchTerm && filteredSpecialists.length > 0 && (
              <ul className="list-group mt-2">
                {filteredSpecialists.map((specialist) => (
                  <li
                    key={specialist.id}
                    className="list-group-item list-group-item-action"
                    role="button"
                    onClick={() =>
                      onSelectSpecialist(
                        `${specialist.name} - ${specialist.especialidad}`,
                        specialist.monto
                      )
                    }
                  >
                    {specialist.name} - {specialist.especialidad} - $
                    {specialist.monto}
                  </li>
                ))}
              </ul>
            )}
          </Form.Group>

          <Form.Group className="mb-4">
            <Form.Label>Razón de la consulta</Form.Label>
            <Form.Control
              as="textarea"
              rows={3}
              {...register("reason", { required: "Razón de la consulta" })}
            />
          </Form.Group>

          <Form.Group className="mb-3 d-flex gap-3" controlId="formBasicDate">
            <Form.Label className="py-2"> Selecciona una fecha: </Form.Label>
            <Controller
              control={control}
              name="appointmentDate"
              rules={{ required: "Selecciona una fecha y hora" }}
              render={({ field }) => (
                <DatePicker
                  selected={field.value}
                  onChange={(date) => field.onChange(date)}
                  className="form-control w-100 px-4"
                  placeholderText="Selecciona una fecha y hora:"
                  dateFormat="dd/MM/yyyy h:mm aa"
                  showTimeSelect
                  timeFormat="HH:mm"
                  timeIntervals={60}
                  timeCaption="Hora"
                  minDate={new Date()}
                  minTime={new Date(new Date().setHours(7, 0))}
                  maxTime={new Date(new Date().setHours(20, 0))}
                />
              )}
            />
            {errors.appointmentDate && (
              <p className="text-danger">{errors.appointmentDate.message}</p>
            )}
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Control
              type="hidden"
              {...register("monto", { required: true })}
            />
          </Form.Group>

          <Button
            variant="primary"
            size="lg"
            className="mb-3 w-50 align-self-end"
            type="submit"
          >
            Reservar Cita
          </Button>
        </fieldset>
      </Form>

      {submittedData && (
        <Modal show={showModal} onHide={() => setShowModal(false)}>
          <Modal.Header closeButton>
            <Modal.Title>Confirmar Reserva</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <p>
              <strong>Paciente:</strong> {submittedData.userName}
            </p>
            <p>
              <strong>Fecha de nacimiento:</strong>{" "}
              {submittedData.userbirthDate}
            </p>
            <p>
              <strong>Edad:</strong> {submittedData.userAge}
            </p>
            <br />
            <p>
              <strong>Especialista:</strong> {submittedData.specialist}
            </p>
            <p>
              <strong>Razón:</strong> {submittedData.reason}
            </p>
            <p>
              <strong>Fecha:</strong>{" "}
              {submittedData.appointmentDate.toLocaleString()}
            </p>
            <p>
              <strong>Monto:</strong> ${submittedData.monto}
            </p>
          </Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={() => setShowModal(false)}>
              Cancelar
            </Button>
            <Button variant="primary" onClick={handleConfirm}>
              Confirmar
            </Button>
          </Modal.Footer>
        </Modal>
      )}
    </>
  );
};

export default ReservationForm;
