package com.bxp.assinment.MetroBookingBackend.Dto;



public class ErrorResponseDto {
    private String message;

    public ErrorResponseDto(final String message) {
        this.message = message;
    }

    public ErrorResponseDto() {
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ErrorResponseDto other)) {
            return false;
        } else {
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$message = this.getMessage();
                Object other$message = other.getMessage();
                if (this$message == null) {
                    return other$message == null;
                } else return this$message.equals(other$message);
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ErrorResponseDto;
    }

    public int hashCode() {
        int PRIME = 1;
        int result = 1;
        Object $message = this.getMessage();
        result = result * 59 + ($message == null ? 43 : $message.hashCode());
        return result;
    }

    public String toString() {
        return "ErrorResponseDto(message=" + this.getMessage() + ")";
    }
}
