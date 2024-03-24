package br.dev.anderanjos;

import br.dev.anderanjos.model.Baggage;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        /**
         * CONTEXT / SETUP
         * I have tried to simulate the following structure:
         * ITINERARY > BAGGAGE
         *
         * Where, itineraries with same id will be transformed into single object
         * containing inbound and outbound baggage's
         *
         * In case that there is no match between lists, there is nothing to update and the element must be inserted in list directly.
         */
        Map<Integer, List<Baggage>> inboundItinerary = new HashMap<>();
        Map<Integer, List<Baggage>> outboundItinerary = new HashMap<>();

        int min = 1;
        int max = 3;
        Random rand = new Random();
        int randomNum = 0;
        List<Baggage> baggages = new ArrayList<>();
        Baggage newBaggage = new Baggage();

        for (int i = 0; i < 4; i++) {
            randomNum = rand.nextInt(max) + min;
            newBaggage = Baggage.BaggageBuilder.aBaggage()
                    .baggageId((long) randomNum)
                    .type("Checked Baggage")
                    .amount(randomNum)
                    .build();

            baggages.add(newBaggage);
        }

        inboundItinerary.put(1, baggages.stream().limit(4).collect(Collectors.toList()));
        inboundItinerary.put(2, baggages.stream().limit(3).collect(Collectors.toList()));
        inboundItinerary.put(3, baggages.stream().limit(2).collect(Collectors.toList()));
        inboundItinerary.put(4, baggages.stream().limit(3).collect(Collectors.toList()));

        outboundItinerary.put(1, baggages.stream().limit(4).collect(Collectors.toList()));
        outboundItinerary.put(2, baggages.stream().limit(2).collect(Collectors.toList()));
        outboundItinerary.put(4, baggages.stream().limit(1).collect(Collectors.toList()));


        /**
         * BUSINESS LOGIC
         * 1. Iterate through inbound list
         * 3. Search for items with the same key in outbound list
         * 4. Merge them into a new list
         */

        // It prints inbound (pretending to be an itinerary, right)
        inboundItinerary.forEach((key, value) ->
                System.out.println("Itinerary (INBOUND): " + key + " -> Baggage:" +
                        value.stream()
                                .map(Baggage::getBaggageId)
                                .collect(Collectors.toList())));
        // It prints outbound
        outboundItinerary.forEach((key, value) ->
                System.out.println("Itinerary (OUTBOUND): " + key + " -> Baggage:" +
                        value.stream()
                                .map(Baggage::getBaggageId)
                                .collect(Collectors.toList())));

        simpleMerge(inboundItinerary, outboundItinerary);
    }

    // It works, but it has side effects since original inbound list will be updated
    public static void simpleMerge(
            final Map<Integer, List<Baggage>> inboundItinerary,
            final Map<Integer, List<Baggage>> outboundItinerary) {

        inboundItinerary.forEach((key, item) -> {
            if (outboundItinerary.containsKey(key)) {
                inboundItinerary.get(key).addAll(outboundItinerary.get(key));
            }
        });
        System.out.println("-------------------------------------------------------------------------------------");
        inboundItinerary.forEach((key, value) ->
                System.out.println("Itinerary (MERGED): " + key + " -> Baggage" +
                        value.stream()
                                .map(Baggage::getBaggageId)
                                .collect(Collectors.toList())));
    }
}
