package com.seif.placesdemo

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.PlaceLikelihood
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FetchPlaceResponse
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.seif.placesdemo.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var placesClient: PlacesClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Places.initialize(requireContext(), BuildConfig.MAPS_API_KEY)
        placesClient = Places.createClient(requireContext())
        // Initialize the AutocompleteSupportFragment.
        val autocompleteFragment =
            childFragmentManager.findFragmentById(R.id.autocomplete_fragment)
                    as AutocompleteSupportFragment

        // Specify the types of place data to return.
        autocompleteFragment.setPlaceFields(listOf(Place.Field.ID, Place.Field.NAME))

        autocompleteFragment.setTypeFilter(TypeFilter.ADDRESS)
        // autocompleteFragment.setTypeFilter(TypeFilter.CITIES)
        autocompleteFragment.setCountries("EG") // when we restrict the result it reduce our cost of using places api

        // Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                Log.i("TAG", "Place: ${place.name}, ${place.id}")
            }

            override fun onError(status: Status) {
                Log.i("TAG", "An error occurred: $status")
            }
        })
        getCurrentPlaceLocations()

    }

    @SuppressLint("MissingPermission")
    private fun getCurrentPlaceLocations() {

        // Use fields to define the data types to return.
        val placeFields: List<Place.Field> = listOf(Place.Field.NAME, Place.Field.ID)
        // Use the builder to create a FindCurrentPlaceRequest.
        val request = FindCurrentPlaceRequest.newInstance(placeFields)
         // Call findCurrentPlace and handle the response (first check that the user has granted permission).
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) ==
            PackageManager.PERMISSION_GRANTED
        ) {
            val placeResponse = placesClient.findCurrentPlace(request)
            placeResponse.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val response = task.result
                    response.placeLikelihoods[0].place.id?.let { getDetailsWithPlaceId(it) } // the id can be null if i not specify it in the placesFields above
                    for (placeLikelihood: PlaceLikelihood in response?.placeLikelihoods
                        ?: emptyList()) { // places around us
                        Log.i(
                            "TAG",
                            "Place '${placeLikelihood.place.name}' has likelihood: ${placeLikelihood.likelihood}"
                        )
                    }
                }
                else {
                    val exception = task.exception
                    if (exception is ApiException) {
                        Log.e("TAG", "Place not found: ${exception.statusCode}")
                    }
                }
            }
        } else {
            // A local method to request required permissions;
            // See https://developer.android.com/training/permissions/requesting
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
        }
    }

    private fun getDetailsWithPlaceId(placeId: String) {
        // Specify the fields to return.
        val placeFields = listOf(Place.Field.ID, Place.Field.NAME, Place.Field.BUSINESS_STATUS, Place.Field.ADDRESS)
        // Construct a request object, passing the place ID and fields array.
        val request = FetchPlaceRequest.newInstance(placeId, placeFields)

        placesClient.fetchPlace(request)
            .addOnSuccessListener { response: FetchPlaceResponse ->
                val place = response.place
                Log.d("TAG", "Place found: ${place.name}, Business Status: ${place.businessStatus}, Address: ${place.address}")
            }.addOnFailureListener { exception: Exception ->
                if (exception is ApiException) {
                    Log.e("TAG", "Place not found: ${exception.message}")
                  //  val statusCode = exception.statusCode
                }
            }
    }
}