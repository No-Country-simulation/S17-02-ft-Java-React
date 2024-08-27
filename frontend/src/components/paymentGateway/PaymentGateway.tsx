export const PaymentGateway = () => {
  return (
    <div className="payment-gateway container mt-5">
      <h2 className="mb-4">Payment Details</h2>
      
      {/* Formulario para la selección del método de pago */}
      <div className="payment-methods mb-4">
        <div className="form-check">
          <input className="form-check-input" type="radio" name="paymentMethod" id="creditCard" value="creditCard" defaultChecked />
          <label className="form-check-label" htmlFor="creditCard">
            Credit Card
          </label>
        </div>
        <div className="form-check">
          <input className="form-check-input" type="radio" name="paymentMethod" id="paypal" value="paypal" />
          <label className="form-check-label" htmlFor="paypal">
            PayPal
          </label>
        </div>
        <div className="form-check">
          <input className="form-check-input" type="radio" name="paymentMethod" id="bankTransfer" value="bankTransfer" />
          <label className="form-check-label" htmlFor="bankTransfer">
            Bank Transfer
          </label>
        </div>
      </div>
      
      {/* Formulario para los detalles de la tarjeta */}
      <div className="credit-card-details">
        <div className="mb-3">
          <label htmlFor="cardNumber" className="form-label">Card Number</label>
          <input type="text" className="form-control" id="cardNumber" name="cardNumber" placeholder="1234 5678 9012 3456" required />
        </div>
        <div className="mb-3">
          <label htmlFor="cardName" className="form-label">Cardholder Name</label>
          <input type="text" className="form-control" id="cardName" name="cardName" placeholder="John Doe" required />
        </div>
        <div className="row mb-3">
          <div className="col">
            <label htmlFor="expiryDate" className="form-label">Expiry Date</label>
            <input type="text" className="form-control" id="expiryDate" name="expiryDate" placeholder="MM/YY" required />
          </div>
          <div className="col">
            <label htmlFor="cvv" className="form-label">CVV</label>
            <input type="text" className="form-control" id="cvv" name="cvv" placeholder="123" required />
          </div>
        </div>
      </div>
      
      {/* Botón para completar el pago */}
      <button type="submit" className="btn btn-primary w-100">Complete Payment</button>
    </div>
  )
}