import React, { useState, useEffect } from "react";
import { useForm, Controller } from "react-hook-form";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import Modal from "react-bootstrap/Modal";
import "react-datepicker/dist/react-datepicker.css";
import DatePicker from "react-datepicker";
import { User } from "../../interfaces/profile";
import { useAuth } from "../../context/context.tsx";
import axios from "axios";

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

type Specialist = {
  specialistId: string;
  specialistCode: string;
  specialtyId: number;
  specialtyName: string;
  bookingPrice: number;
  specialistName: string;
  specialistLastname: string;
  reputation: number;
};

interface Schedule {
  scheduleId: string;
  date: string;
  startTime: {
    hour: number;
    minute: number;
    second: number;
    nano: number;
  };
  endTime: {
    hour: number;
    minute: number;
    second: number;
    nano: number;
  };
  specialistId: string;
  scheduleConfigId: number;
}

const ReservationForm: React.FC<BookingFormProps> = ({ user }) => {
  const {
    register,
    handleSubmit,
    control,
    setValue,
    formState: { errors },
  } = useForm<FormData>();

  const { token } = useAuth(); // Usa el hook useAuth

  const [specialists, setSpecialists] = useState<Specialist[]>([]);
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
    const fetchSpecialists = async () => {
      try {
        const response = await axios.get("/api/specialist", {
          headers: {
            Authorization: `Bearer ${token}`, // Incluye el token si es necesario
            "Content-Type": "application/json",
          },
        });
        if (response.data && Array.isArray(response.data.content)) {
          setSpecialists(response.data.content); // Accede a la lista de especialistas
        } else {
          console.error("Formato de datos inesperado:", response.data);
        }
      } catch (error) {
        console.error("Error al obtener los especialistas:", error);
      }
    };

    fetchSpecialists();
  }, [token]);

  const onSubmit = (data: FormData) => {
    console.log("Datos del formulario:", data);
    setSubmittedData(data);
    setShowModal(true);
  };

  const handleConfirm = async () => {
    if (submittedData) {
      // Prepara los datos para el POST
      const schedule: Schedule = {
        scheduleId: "3fa85f64-5717-4562-b3fc-2c963f66afa6", // Puedes obtener esto de alguna manera dinámica
        date: submittedData.appointmentDate.toISOString().split("T")[0], // Formato de fecha: YYYY-MM-DD
        startTime: {
          hour: submittedData.appointmentDate.getHours(),
          minute: submittedData.appointmentDate.getMinutes(),
          second: 0,
          nano: 0,
        },
        endTime: {
          hour: submittedData.appointmentDate.getHours() + 1, // Asumiendo que la cita dura 1 hora
          minute: submittedData.appointmentDate.getMinutes(),
          second: 0,
          nano: 0,
        },
        specialistId: submittedData.specialist,
        scheduleConfigId: 0, // Este valor debe ser ajustado según tus necesidades
      };

      try {
        const response = await axios.post(
          "/api/bookings",
          { schedule },
          {
            headers: {
              Authorization: `Bearer ${token}`, // Incluye el token si es necesario
              "Content-Type": "application/json",
            },
          }
        );
        console.log("Reserva confirmada:", response.data);
        setShowModal(false);
      } catch (error) {
        console.error("Error al confirmar la reserva:", error);
      }
    }
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
          <legend className="mb-3">Selecciona un especialista</legend>

          <Form.Group className="mb-3" controlId="formBasicSpecialist">
            <Form.Label>Especialista</Form.Label>
            <Form.Control
              as="select"
              {...register("specialist", {
                required: "Selecciona un especialista",
              })}
              onChange={(e) => {
                const selectedSpecialist = specialists.find(
                  (spec) => spec.specialistId === e.target.value
                );
                if (selectedSpecialist) {
                  setValue("monto", selectedSpecialist.bookingPrice);
                }
              }}
            >
              <option value="">Seleccione un especialista</option>
              {specialists.map((specialist) => (
                <option
                  key={specialist.specialistId}
                  value={specialist.specialistId}
                >
                  {specialist.specialistName} {specialist.specialistLastname} -{" "}
                  {specialist.specialtyName} - ${specialist.bookingPrice}
                </option>
              ))}
            </Form.Control>
            {errors.specialist && (
              <p className="text-danger">{errors.specialist.message}</p>
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
            className="btn-navbar-pct"
            type="submit"
          >
            Reservar Cita
          </Button>
        </fieldset>
      </Form>

      <Modal show={showModal} onHide={() => setShowModal(false)}>
        <Modal.Header closeButton>
          <Modal.Title>Confirmar Reserva</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <p>Nombre: {submittedData?.userName}</p>
          <p>Edad: {submittedData?.userAge}</p>
          <p>Fecha de nacimiento: {submittedData?.userbirthDate}</p>
          <p>Género: {submittedData?.userGender}</p>
          <p>Especialista: {submittedData?.specialist}</p>
          <p>Razón: {submittedData?.reason}</p>
          <p>
            Fecha de la cita: {submittedData?.appointmentDate.toLocaleString()}
          </p>
          <p>Monto: ${submittedData?.monto}</p>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" className="btn-navbar-prof" onClick={() => setShowModal(false)}>
            Cancelar
          </Button>
          <Button variant="primary" className="btn-navbar-pct" onClick={handleConfirm}>
            Confirmar
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
};

export default ReservationForm;
