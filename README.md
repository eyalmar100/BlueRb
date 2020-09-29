# BlueRb

Tests
There are Tests ( 6 ) :
Positive tests and Negative tests


API to validate coupon:
------------------------------
POST: http://localhost:9090/api/v1/coupons/validate
Sample request:
{
    "couponId":"coup2",
    "price":99
}

Sample response (Success):
{
    "discountedPrice": 87,
    "message": "Discount Applied!"
}

Sample response (Failure):
{
    "discountedPrice": 99,
    "message": "Invalid Coupon!"
}

============================================================
Check Ticket Availability:
--------------------------------------
POST: http://localhost:9090/api/v1/tickets/availability
Sample request:
{
    "ticketId":"1"
}

Sample response (Success):
{
    "available": true
}

Sample response (Failure):
{
    "available": false
}

============================================================
User checkin:
------------------------
POST: http://localhost:9090/api/v1/users/checkin
Sample request:
{
    "baggageId":"user2_bag1",
    "destinationId":"dest-new"
}

Sample response (Success):
{
    "checkedIn": true
}

Sample response (Failure):
{
    "checkedIn": false
}

============================================================
