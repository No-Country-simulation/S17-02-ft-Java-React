import { Outlet } from "react-router-dom";
import SidebarClient from "../../components/DashboardClient/SidebarClient";


const DashboardLayout = () => {
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
