import React from "react";
import ProfileInfo from "../../components/profile/profileInfo";
import PaymentMethod from "../../components/profile/PaymentMethod";
import ProfileActions from "../../components/profile/ProfileActions";
import Setting from "../../components/profile/Setting";
import Support from "../../components/profile/Support";
import Logout from "../../components/profile/Logout";

const Profile: React.FC = () => {
  return (
    <div>
      <ProfileInfo />
      <ProfileActions />
      <PaymentMethod />
      <Setting />
      <Support />
      <Logout />
    </div>
  );
};

export default Profile;
