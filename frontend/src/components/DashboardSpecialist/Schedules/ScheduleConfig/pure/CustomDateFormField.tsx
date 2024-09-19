import { useState } from "react";
import { ErrorMessage, FormikErrors, FormikTouched } from "formik";
import { DateRange, DayPicker, Matcher } from "react-day-picker";
import { Form, Popover, OverlayTrigger } from "react-bootstrap";
import { format } from "date-fns";
import "bootstrap-icons/font/bootstrap-icons.css"; // Importación de iconos de Bootstrap

interface CustomDateRangeFormFieldProps {
  readonly values: DateRange;
  readonly setFieldValue: (
    field: string,
    value: Date,
    shouldValidate?: boolean
  ) => Promise<void | FormikErrors<any>>;
  readonly touched: FormikTouched<any>;
  readonly errors: FormikErrors<any>;
  readonly fieldNameFrom: string;
  readonly fieldNameTo: string;
  readonly labelFrom: string;
  readonly labelTo: string;
  readonly placeholderFrom?: string;
  readonly placeholderTo?: string;
  readonly disabled: Matcher | Matcher[] | undefined;
}

export default function CustomDateRangeFormField({
  values,
  setFieldValue,
  touched,
  errors,
  fieldNameFrom,
  fieldNameTo,
  labelFrom,
  labelTo,
  placeholderFrom = "",
  placeholderTo = "",
  disabled,
}: CustomDateRangeFormFieldProps) {
  const [showPopover, setShowPopover] = useState(false);
  console.log(labelFrom + labelTo)
  return (
    <div className="custom-date-range">
      <OverlayTrigger
        trigger="click"
        placement="bottom"
        overlay={
          <Popover id="popover-positioned-bottom">
            <Popover.Body>
              <DayPicker
                mode="range"
                required
                selected={{ from: values.from, to: values.to }}
                onSelect={(selected: DateRange) => {
                  setFieldValue(fieldNameFrom, selected?.from ?? new Date(), true);
                  setFieldValue(fieldNameTo, selected?.to ?? new Date(), true);
                }}
                showOutsideDays
                disabled={disabled}
              />
            </Popover.Body>
          </Popover>
        }
      >
        <Form.Control
          type="text"
          value={
            values.from
              ? format(values.from, "yyyy-MM-dd") + " - " + format(values.to ?? new Date(), "yyyy-MM-dd")
              : ""
          }
          placeholder={placeholderFrom + " - " + placeholderTo}
          onClick={() => setShowPopover(!showPopover)}
          readOnly
          className="custom-input"
        />
      </OverlayTrigger>

      {touched[fieldNameFrom] && errors[fieldNameFrom] && touched[fieldNameTo] && errors[fieldNameTo] ? (
        <div className="form-error">
          <ErrorMessage name={fieldNameFrom} component="div" className="error-message" />
          <ErrorMessage name={fieldNameTo} component="div" className="error-message" />
        </div>
      ) : (
        <div className="spacer" />
      )}
    </div>
  );
}
