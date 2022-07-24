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
