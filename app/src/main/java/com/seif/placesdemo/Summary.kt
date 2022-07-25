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