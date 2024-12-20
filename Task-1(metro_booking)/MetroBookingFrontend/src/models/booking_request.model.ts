export interface BookingRequest {
  passengerEmail: string;
  passengerMobile?: string;
  fromStationNumber: number;
  toStationNumber: number;
}