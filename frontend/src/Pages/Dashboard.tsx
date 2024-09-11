import { Outlet } from "react-router-dom";
import DashboardLayout from "../layout/DashboardLayout";

const Dashboard = () => {
  return (
    <DashboardLayout>
      <Outlet />
    </DashboardLayout>
  );
};

export default Dashboard;
