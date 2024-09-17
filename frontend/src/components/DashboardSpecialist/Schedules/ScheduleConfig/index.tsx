import { Formik, Field, Form, FormikHelpers } from 'formik';
import * as Yup from 'yup';

import { format } from 'date-fns';
import CustomDateRangeFormField from './pure/CustomDateFormField';
import CustomFormField from './pure/CustomFormField';
import { CustomChip } from './pure/CustomChip';

interface ScheduleConfigFormValues {
  startDate: Date;
  endDate: Date;
  startTime: string;
  endTime: string;
  startRestTime: string;
  endRestTime: string;
  timeBooking: number;
  timeBookingRest: number;
  selectedDays: string[];
}

const scheduleConfigSchema = Yup.object().shape({
  startDate: Yup.date()
    .min(new Date(), 'La fecha inicial debe ser después de hoy')
    .required('La fecha inicial es obligatoria'),
  endDate: Yup.date()
    .min(Yup.ref('startDate'), 'La fecha final debe ser posterior a la inicial')
    .required('La fecha final es obligatoria'),
  startTime: Yup.string()
    .matches(/^([01]\d|2[0-3]):([0-5]\d)$/, 'La hora debe estar en formato HH:mm')
    .required('La hora inicial es obligatoria'),
  endTime: Yup.string()
    .matches(/^([01]\d|2[0-3]):([0-5]\d)$/, 'La hora debe estar en formato HH:mm')
    .test('is-after-start-time', 'La hora de fin debe ser después de la hora de inicio', function (value) {
      const { startTime } = this.parent;
      return startTime && value ? value > startTime : false;
    })
    .required('La hora final es obligatoria'),
  startRestTime: Yup.string()
    .matches(/^([01]\d|2[0-3]):([0-5]\d)$/, 'La hora debe estar en formato HH:mm')
    .required('La hora de inicio de descanso es obligatoria'),
  endRestTime: Yup.string()
    .matches(/^([01]\d|2[0-3]):([0-5]\d)$/, 'La hora debe estar en formato HH:mm')
    .required('La hora de fin de descanso es obligatoria'),
  timeBooking: Yup.number()
    .positive('La duración de trabajo debe ser positiva')
    .integer('La duración debe ser un número entero')
    .required('La duración de trabajo es obligatoria'),
  timeBookingRest: Yup.number()
    .positive('La duración del descanso debe ser positiva')
    .integer('La duración debe ser un número entero')
    .required('La duración del descanso es obligatoria'),
  selectedDays: Yup.array()
    .of(Yup.string())
    .min(1, 'Debes seleccionar al menos un día')
    .required('Debes seleccionar al menos un día'),
});

const days = [
  { name: 'Lunes', value: 'MONDAY' },
  { name: 'Martes', value: 'TUESDAY' },
  { name: 'Miércoles', value: 'WEDNESDAY' },
  { name: 'Jueves', value: 'THURSDAY' },
  { name: 'Viernes', value: 'FRIDAY' },
  { name: 'Sábado', value: 'SATURDAY' },
  { name: 'Domingo', value: 'SUNDAY' },
];

const formatDay = (date: Date) => format(date, 'yyyy-MM-dd');

export default function ScheduleConfig() {
  const initialCredentials: ScheduleConfigFormValues = {
    startDate: new Date(),
    endDate: new Date(),
    startTime: "00:00",
    endTime: "00:00",
    startRestTime: "00:00",
    endRestTime: "00:00",
    timeBooking: 0,
    timeBookingRest: 0,
    selectedDays: [],
  };

  const handleSubmit = async (
    values: ScheduleConfigFormValues,
    { setSubmitting }: FormikHelpers<ScheduleConfigFormValues>
  ) => {
    setSubmitting(false);
    console.log(values);
  };

  return (
    <div className="custom-schedule-config">
      <h4 className="custom-schedule-config__title">Configura tu Horario de Trabajo</h4>
      <Formik
        initialValues={initialCredentials}
        validationSchema={scheduleConfigSchema}
        onSubmit={handleSubmit}
      >
        {({ touched, errors, values, setFieldValue }) => 
          (<Form className="custom-schedule-config__form">
            <div className="custom-schedule-config__card">
              <div className="custom-schedule-config__body">
                <CustomDateRangeFormField
                  values={{ from: values.startDate, to: values.endDate }}
                  setFieldValue={setFieldValue}
                  touched={touched}
                  errors={errors}
                  fieldNameFrom="startDate"
                  fieldNameTo="endDate"
                  placeholderFrom="Fecha de Inicio"
                  placeholderTo="Fecha de Fin de trabajo"
                  labelFrom="Fecha de Inicio"
                  labelTo="Fecha de Fin de trabajo"
                  disabled={{ before: new Date() }}
                />

                <div className="custom-schedule-config__time-fields">
                  <Field
                    name="startTime"
                    component={CustomFormField}
                    placeholder="Hora de Inicio de trabajo"
                    label="Hora de Inicio de trabajo"
                    type="time"
                  />
                  <Field
                    name="endTime"
                    component={CustomFormField}
                    placeholder="Hora de Fin de trabajo"
                    label="Hora de Fin de trabajo"
                    type="time"
                  />
                </div>

                <div className="custom-schedule-config__rest-time-fields">
                  <Field
                    name="startRestTime"
                    component={CustomFormField}
                    placeholder="Hora de Inicio de descanso"
                    label="Hora de Inicio de descanso"
                    type="time"
                  />
                  <Field
                    name="endRestTime"
                    component={CustomFormField}
                    placeholder="Hora de Fin de descanso"
                    label="Hora de Fin de descanso"
                    type="time"
                  />
                </div>

                <div className="custom-schedule-config__durations">
                  <Field
                    name="timeBooking"
                    component={CustomFormField}
                    placeholder="Duración trabajo (min)"
                    label="Duración de trabajo (min)"
                    type="number"
                  />
                  <Field
                    name="timeBookingRest"
                    component={CustomFormField}
                    placeholder="Duración descanso (min)"
                    label="Duración de descanso (min)"
                    type="number"
                  />
                </div>

                <h5 className="custom-schedule-config__days-title">Selecciona los días de trabajo</h5>
                <div className="custom-schedule-config__days">
                  {days.map((day) => (
                    <CustomChip
                      key={day.value}
                      value={day}
                      setFieldValue={setFieldValue}
                      selectedDays={values.selectedDays}
                    />
                  ))}
                </div>
              </div>
              <div className="custom-schedule-config__footer">
                <button
                  type="submit"
                  className="custom-schedule-config__submit-btn"
                >
                  Establecer horario de trabajo
                </button>
              </div>
            </div>
          </Form>)
        }
      </Formik>
    </div>
  );
}
