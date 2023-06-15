package io.padam_exercise.padamdaily.screens

import com.google.android.gms.maps.model.LatLng
import io.padam_exercise.padamdaily.models.MarkerType
import io.padam_exercise.padamdaily.models.Suggestion

/**
 * Map interface
 * Implement this in your page where there is a map to use map methods
 */
interface MapActionsDelegate {

    fun updateMap(vararg latLngArgs: LatLng?)
    // changed function attributes to call updateMarker once in SearchItineraryActivity
    // fun updateMarker(markerType: MarkerType, suggestion: Suggestion)
    fun updateMarker(markerTypes: List<MarkerType>, suggestions: List<Suggestion>)

    fun clearMap()


}
