package io.padam_exercise.padamdaily.screens

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import io.padam_exercise.padamdaily.R
import io.padam_exercise.padamdaily.databinding.ActivitySearchItineraryBinding
import io.padam_exercise.padamdaily.models.MarkerType
import io.padam_exercise.padamdaily.models.Suggestion
import io.padam_exercise.padamdaily.models.mocking.MockSuggestion
import io.padam_exercise.padamdaily.utils.Toolbox


class SearchItineraryActivity : AppCompatActivity() {

    private var mMapDelegate: MapActionsDelegate? = null
    // enabled viewbinding because I got problems when I wanted to update dependencies versions!
    private lateinit var searchItineraryBinding: ActivitySearchItineraryBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //viewbinding
        searchItineraryBinding = ActivitySearchItineraryBinding.inflate(layoutInflater)
        val rootView = searchItineraryBinding.root
        setContentView(rootView)

        initMapFragment()
        initView()
    }

    private fun initMapFragment() {
        val mapFragment: MapFragment = MapFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.cl_map, mapFragment)
            .commitAllowingStateLoss()

        mMapDelegate = mapFragment
    }

    private fun initView() {
        initSpinners()
        manageOnClickSearchItinerary()
    }

    private fun initSpinners() {
        initDepartureSpinner()
        initArrivalSpinner()
    }

    private fun initDepartureSpinner() {
        val departuresList = Toolbox.getStringListFromSuggestion(MockSuggestion.departures())
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, departuresList)
        searchItineraryBinding.spinnerDeparture.adapter = adapter
    }

    private fun initArrivalSpinner() {
        // TODO: Implement data

        /* Implementation of the 'arrival spinner' by filling it with 'arrivalList'
          setting up the adapter for spinner
         */
        val arrivalList = Toolbox.getStringListFromSuggestion(MockSuggestion.arrivals())
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrivalList)
       // spinner_arrival.adapter = adapter
        searchItineraryBinding.spinnerArrival.adapter = adapter
    }

    private fun manageOnClickSearchItinerary() {
        searchItineraryBinding.btnSearchItinerary.setOnClickListener {
            mMapDelegate?.clearMap()

            val selectedDeparture: String = searchItineraryBinding.spinnerDeparture.selectedItem.toString()
            //added new val to retrieve spinner value on String format
            val selectedArrival: String = searchItineraryBinding.spinnerArrival.selectedItem.toString()

            // changed val suggestion to val departure to avoid confusion in my code
            val departure = getSuggestionFromSelection(selectedDeparture, MockSuggestion.departures())

            // 2. new value to retrieve Suggestion object by getSuggestionFromSelection function
            val arrival = getSuggestionFromSelection(selectedArrival, MockSuggestion.arrivals())

            // new values list of Suggestions and MarkerTypes
            val markerTypes = listOf(MarkerType.DEPARTURE, MarkerType.ARRIVAL)
            val suggestions = listOf(departure, arrival)



            //3. created 2 new val suggestions and markerTypes to send suggestions and markertype once !
            // and together!
            mMapDelegate?.updateMarker(markerTypes, suggestions)
            mMapDelegate?.updateMap(departure.latLng, arrival.latLng)
        }
    }

    private fun getSuggestionFromSelection(selection: String, suggestionList: ArrayList<Suggestion>): Suggestion {
        for (suggestion in suggestionList) {
            if (suggestion.name == selection) {
                return suggestion
            }
        }
        return suggestionList.first()
    }




}