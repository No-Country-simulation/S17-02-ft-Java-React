import { initMercadoPago, StatusScreen, Wallet } from '@mercadopago/sdk-react';
import { useEffect, useState } from 'react';
import api from '../../utils/axios';

type DataReserva = {
  id: string; // ID DE LA RESERVA A PAGAR
  title: string; // TITULO QUE SE DESEE.
  description: string; // DESCRIPCION A ELEGIR
  currencyId: string; // MONEDA DE LA TRANSFERENCIA, EN ESTE CASO SOLO USAMOS 'ARS'
  unitPrice: number; // EL PRECIO UNITARIO.
  categoryId: string; //
};

export default function MercadoPago() {
  const [preferenceId, setPreferenceId] = useState<string | null>(null);
  useEffect(() => {
    initMercadoPago(import.meta.env.VITE_API_MP_TOKEN_PRO, {
      locale: 'es-AR',
    });
  }, []);

  const dataInit: DataReserva = {
    id: '1e7bb802-b9ae-4ed0-a327-2b86456ebca3',
    currencyId: 'ARS',
    description: 'Una reserva con el especialista..',
    unitPrice: 12000,
    title: 'Este es un titulo para generar el id',
    categoryId: 'Un id cualquiera',
  };

  const createBtnMP = async (formData: DataReserva) => {
    console.log(formData);
    try {
      const { data } = await api.post(
        'mercado-pago/generate-preference',
        formData
      );
      console.log(data);
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
      <button
        className='btn btn-primary'
        onClick={async () => await createBtnMP(dataInit)}>
        Generar boton de mercado pago
      </button>
      {preferenceId !== null && (
        <Wallet
          initialization={{
            preferenceId: preferenceId,
            redirectMode: 'modal',
          }}
        />
      )}
      <StatusScreen initialization={{ paymentId: '86888772094' }} />
    </>
  );
}
