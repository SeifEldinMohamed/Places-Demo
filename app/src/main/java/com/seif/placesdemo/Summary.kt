package com.seif.placesdemo

/** video 11 : Autocomplete Widget Fragment **/
// Option 1: Embed an AutocompleteSupportFragment
// The autocomplete service in the Places SDK for Android returns place predictions in response
// to user search queries. As the user types, the autocomplete service returns suggestions
// for places such as businesses, addresses, plus codes, and points of interest.
//
// You can add autocomplete to your app in the following ways:
// - Add an autocomplete widget to save development time and ensure a consistent user experience.
// - Get place predictions programmatically to create a customized user experience.

/** video 12 : Autocomplete Widget Activity **/
// Option 2: Use an intent to launch the autocomplete activity (Note: These steps aren't needed if you are embedding a fragment.)
// If you want your app to use a different navigational flow
// (for example, to trigger the autocomplete experience from an icon rather than a search field),
// your app can launch autocomplete by using an intent.
// Caution: Do NOT launch the AutocompleteActivity directly. Instead, use an intent to launch Autocomplete
// (PlaceAutocomplete in the compatibility library).
//
// To launch the autocomplete widget using an intent, follow these steps:
// 1- Use Autocomplete.IntentBuilder to create an intent, passing the desired Autocomplete mode.
// The intent must call startActivityForResult, passing in a request code that identifies the intent.
// 2- Override the onActivityResult callback to receive the selected place.

/** video 13 : Constraint AutoComplete Result **/

// - Constrain autocomplete results
// You can constrain autocomplete results to a specific geographic region, and/or filter the results to one or more place types, or to up to five countries. You can apply these constraints to the autocomplete activity, AutocompleteSupportFragment, and programmatic autocomplete APIs.
//
// To constrain results, do the following:
//
// To prefer results within the defined region, call "setLocationBias()" (some results from outside the defined region may still be returned).
// To only show results within the defined region, call "setLocationRestriction()" (only results within the defined region will be returned). // limit result
// To return only results that conform to a particular place type, call "setTypeFilter()" (for example, specifying TypeFilter.ADDRESS will return only results with a precise address).
// To return only results within up to five specified countries, call "setCountries()". Countries must be passed as a two-character, ISO 3166-1 Alpha-2 compatible country code.

// Note: This restriction is only applied to entire routes, synthetic results located outside the rectangular bounds may be returned based on a route that overlaps with the location restrict.

// Filter results by place type
// You can restrict results from an autocomplete request so they only return a certain place type. If the results aren’t restricted, all types are returned. In general only a single type is allowed. The exception is that you can safely mix the GEOCODE and ESTABLISHMENT types; however, this has the same effect as specifying no types.
//
// To filter autocomplete results to a specific place type, call setTypeFilter() to set the filter to use. Then, pass the filter to a fragment or intent.
//
// The following code example shows calling setTypeFilter() on an AutocompleteSupportFragment to set a filter returning only results with a precise address.

/** video 14 : get the current place and discover place at the devices current user location **/
// Using the Places SDK for Android, you can discover the place at the device's currently-reported location.
// Examples of places include local businesses, points of interest, and geographic locations.

// To use the library you do not need to declare any additional permissions in your app's manifest,
// as the library declares all permissions it uses in its manifest. However, if your app uses PlacesClient.findCurrentPlace(), you must request location permissions at runtime.
//
// If your app does not use PlacesClient.findCurrentPlace(), explicitly remove the ACCESS_FINE_LOCATION permission introduced
// by the library by adding the following to your manifest:

// To find the local business or other place where the device is currently located, follow these steps:
//
// Call ContextCompat.checkSelfPermission to check whether the user has granted permission to access their device location. Your app must also include code to prompt the user for permission, and to handle the result. See Request App Permissions for details.
// Create a FindCurrentPlaceRequest, passing a List of Place.Fields, specifying the place data types your app should request.
// Call PlacesClient.findCurrentPlace(), passing the FindCurrentPlaceRequest you created in the previous step.
// Get the list of PlaceLikelihoods from the FindCurrentPlaceResponse.

// - Note: findCurrentPlace() does NOT support the following fields: Place.Field.ADDRESS_COMPONENTS, Place.Field.OPENING_HOURS, Place.Field.PHONE_NUMBER, Place.Field.UTC_OFFSET, and Place.Field.WEBSITE_URI.
// Fields correspond to Place Search results, and are divided into three billing categories: Basic, Contact, and Atmosphere. Basic fields are billed at base rate, and incur no additional charges. Contact and Atmosphere fields are billed at a higher rate. For more information about how Place data requests are billed, see Usage and Billing.
//
// The API returns a FindCurrentPlaceResponse in a Task. The FindCurrentPlaceResponse contains a list of PlaceLikelihood objects representing places where the device is likely to be located. For each place, the result includes an indication of the likelihood that the place is the right one. The list may be empty, if there is no known place corresponding to the given device location.
//
// You can call PlaceLikelihood.getPlace() to retrieve a Place object, and PlaceLikelihood.getLikelihood() to get the place's likelihood rating. A higher value means a greater probability that the place is the best match.
//
// The following code sample retrieves the list of places where the device is most likely to be located, and logs the name and likelihood for each place.

/** video 15 : get a place by id **/
//
// The Places SDK for Android provides your app with rich information about places, including the place's name and address,
// the geographical location specified as latitude/longitude coordinates, the type of place (such as night club, pet store, museum),
// and more. To access this information for a specific place, you can use the place ID, a stable identifier that uniquely identifies a place.
//
// - Place details
// The Place object provides information about a specific place. You can get hold of a Place object in the following ways:
//
// - Call PlacesClient.findCurrentPlace() – See the guide to getting the current place.
// - Call PlacesClient.fetchPlace() – See the guide to getting a place by ID.
//
// - When you request a place, you must specify which types of place data to return.
// To do this, pass a List<Place.Field>, specifying the data types to return.
// This is an important consideration, since this will affect the cost for each request.
// Because place data results cannot be empty, only place results with data are returned
// (for example, if a requested place has no photos, the photos field will not be present
// in the result). You can specify one or more of the following fields:
//
// Place.Field.ADDRESS
// Place.Field.ADDRESS_COMPONENTS
// Place.Field.BUSINESS_STATUS
// Place.Field.ID
// Place.Field.LAT_LNG
// Place.Field.NAME
// Place.Field.OPENING_HOURS
// Place.Field.PHONE_NUMBER
// Place.Field.PHOTO_METADATAS
// Place.Field.PLUS_CODE
// Place.Field.PRICE_LEVEL
// Place.Field.RATING
// Place.Field.TYPES
// Place.Field.USER_RATINGS_TOTAL
// Place.Field.ICON_URL
// Place.Field.ICON_BACKGROUND_COLOR
// Place.Field.VIEWPORT
// Place.Field.UTC_OFFSET
// Place.Field.WEBSITE_URI