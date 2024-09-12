import { Outlet } from "react-router-dom";
import Sidebar from "../../components/DashboardSpecialist/SideBar";

const DashboardLayout = () => {
  return (
    <div className="container-fluid">
      <div className="row">
        <Sidebar />
        <div className="col bg-light p-4">
          <Outlet />
        </div>
      </div>
    </div>
  );
};

export default DashboardLayout;
