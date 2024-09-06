import { useContext } from "react";
import BookingForm from "../components/Booking/BookingForm";
import { UserContext } from "../context/userContext";

const Booking = () => {
  const userContext = useContext(UserContext);

  if (!userContext) {
    return <div>Error: Usuario no encontrado</div>;
  }

  const { user } = userContext;
  
  return (
    <section className="container p-5 d-flex flex-column align-items-center">
      <div className="mb-5">
        <h1>Nueva reservas</h1>
      </div>

      <BookingForm user={user} />
    </section>
  );
};

export default Booking;
