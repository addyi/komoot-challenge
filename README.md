# komoot Senior Android Developer challenge

- First break down the problem into chunks


- First reading official documentation about
    - getting location
      updates: https://developer.android.com/develop/sensors-and-location/location/request-updates
    - access location in the
      background: https://developer.android.com/develop/sensors-and-location/location/background
    - Background location
      limits: https://developer.android.com/about/versions/oreo/background-location-limits
      I knew that there is a problem if the phone is locked to get update of the location because of
      power saving concerns.
      I was thinking about using Geofences if I don't get location updates in the background. But
      then I found this info:

> If your app is running in the foreground, there is no change in location sampling rates compared
> to Android 7.1.1 (API level 25).

Funny thing is you don't even necessarily need to ask for notification permission for the foreground
service. 
