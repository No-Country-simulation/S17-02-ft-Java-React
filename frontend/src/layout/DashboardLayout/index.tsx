import { Outlet } from "react-router-dom";
import { useAuth } from "../../context/context.tsx";
import SidebarClient from "../../components/DashboardClient/SidebarClient/index.tsx";
import Sidebar from "../../components/DashboardSpecialist/SideBar/index.tsx";

const DashboardLayout = () => {
  const { roleId } = useAuth();

  let SidebarComponent = null;
  if (roleId === "9c765b7d-9eec-421b-85c6-6d53bcd002da") {
    SidebarComponent = Sidebar;
  } else if (roleId === "2326ec2c-4f97-4007-b52c-ba5561b434b9") {
    SidebarComponent = SidebarClient;
  }

  return (
    <div className="container-fluid">
      <div className="row">
        {SidebarComponent && <SidebarComponent />}
        <div className="col bg-light p-4">
          <Outlet />
        </div>
      </div>
    </div>
  );
};

export default DashboardLayout;
