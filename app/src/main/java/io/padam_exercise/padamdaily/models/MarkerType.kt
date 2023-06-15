package io.padam_exercise.padamdaily.models

import com.google.android.gms.maps.model.BitmapDescriptorFactory

//Departure Marker displayed in RED color, Arrival marker displayed in BLUE
enum class MarkerType(val color: Float) {
    DEPARTURE(BitmapDescriptorFactory.HUE_RED),
    ARRIVAL(BitmapDescriptorFactory.HUE_BLUE)
}

