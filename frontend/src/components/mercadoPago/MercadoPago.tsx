import { initMercadoPago, Wallet } from '@mercadopago/sdk-react';
import axios from 'axios';
import { useEffect, useState } from 'react';

type DataReserva = {
  id: string; // ID DE LA RESERVA A PAGAR
  title: string; // TITULO QUE SE DESEE.
  description: string; // DESCRIPCION A ELEGIR
  currencyId: string; // MONEDA DE LA TRANSFERENCIA, EN ESTE CASO SOLO USAMOS 'ARS'
  priceUnit: number; // EL PRECIO UNITARIO.
  categoryId: string; //
};

export default function MercadoPago() {
  const [preferenceId, setPreferenceId] = useState<string | null>(null);
  useEffect(() => {
    initMercadoPago(import.meta.env.VITE_API_MP_TOKEN_PRO);
  }, []);

  const dataInit: DataReserva = {
    id: 'ID DE LA TRANSFERENCIA',
    currencyId: 'ARS',
    description: 'Una reserva con el especialista..',
    priceUnit: 12000,
    title: 'Este es un titulo para generar el id',
    categoryId: 'Un id cualquiera',
  };

  const createBtnMP = async (formData: DataReserva) => {
    try {
      const { data } = await axios.post<string>(
        'http://localhost:8090/api/mercado-pago/generate-preference',
        formData
      );
      if (data) {
        setPreferenceId(data);
      }
    } catch (error) {
      console.log(error);
    }
  };
  return (
    <>
      <div>MercadoPago</div>
      <button className='btn btn-primary' onClick={() => createBtnMP(dataInit)}>
        Generar boton de mercado pago
      </button>
      {preferenceId !== null && (
        <Wallet initialization={{ preferenceId, redirectMode: 'modal' }} />
      )}
    </>
  );
}
