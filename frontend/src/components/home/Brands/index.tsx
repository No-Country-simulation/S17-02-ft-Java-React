// import Hp from "../../../assets/images/Brands/marcaHp.png";
// import Natura from "../../../assets/images/Brands/marcaNatura.png";
// import Sanofi from "../../../assets/images/Brands/marcaSanofi.png";
import Aciu from "../../../assets/images/Brands/marcaAciu.png";
import CruzRoja from "../../../assets/images/Brands/marcaHartmann.png";
import Crecimg from "../../../assets/images/Brands/marcaNatura.png";
import Vithas from "../../../assets/images/Brands/marcaVithas.png";

const Brands = () => {
  return (
    <div className="container-fluid  mt-5 p-5 mx-auto">
      <div className="row mb-5">
        <h2 className="title text-center">Nuestras marcas</h2>
      </div>
      <div className="row justify-content-center gap-5">
        <div className="col-6 col-sm-4 col-md-3 col-lg-2 d-flex justify-content-center">
          <img src={Crecimg} className="w-75 h-auto img-fluid" alt="Crecimg" />
        </div>
        <div className="col-6 col-sm-4 col-md-3 col-lg-2 d-flex justify-content-center">
          <img src={Aciu} className="w-75 h-auto img-fluid" alt="Aciu" />
        </div>
        <div className="col-6 col-sm-4 col-md-3 col-lg-2 d-flex justify-content-center">
          <img src={CruzRoja} className="w-75 h-auto img-fluid" alt="Cruz Roja" />
        </div>
        
        <div className="col-6 col-sm-4 col-md-3 col-lg-2 d-flex justify-content-center">
          <img src={Vithas} className="w-75 h-auto img-fluid" alt="Vithas" />
        </div>
      </div>
    </div>
  );
};

export default Brands;
