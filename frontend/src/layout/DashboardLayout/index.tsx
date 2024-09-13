import { Outlet } from "react-router-dom";
import SidebarClient from "../../components/DashboardClient/SidebarClient";


const DashboardLayout = () => {
   // const [token, setTokenState] = useState<string | null>(
  //   localStorage.getItem("token") || null
  // );
  // if (!token) {
  //   Swal.fire("Error", "Debe iniciar sesión", "error");
  //   return <Navigate to="/login" />
  // }
  return (
    <div className="container-fluid">
      <div className="row">
        <SidebarClient />
        <div className="col bg-light p-4">
          <Outlet />
        </div>
      </div>
    </div>
  );
};

export default DashboardLayout;
