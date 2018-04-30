package com.example.mdtho.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class FactActivity extends AppCompatActivity {
    int current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts);

        final String[] catFacts = new String[100];
        catFacts[0] = "Cats come back to full alertness from the sleep state faster than any other creature.";
        catFacts[1] = "There are approximately 100 breeds of cat.";
        catFacts[2] = "Abraham Lincoln loved cats. He had four of them while he lived in the White House.";
        catFacts[3] = "Many cats cannot properly digest cow's milk. Milk and milk products give them diarrhea.";
        catFacts[4] = "A cat can’t climb head first down a tree because every claw on a cat’s paw points the same way. To get down from a tree, a cat must back down.";
        catFacts[5] = "A cat’s nose pad is ridged with a unique pattern, just like the fingerprint of a human.";
        catFacts[6] = "Approximately 1/3 of cat owners think their pets are able to read their minds.";
        catFacts[7] = "Many Egyptians worshipped the goddess Bast, who had a woman’s body and a cat’s head.";
        catFacts[8] = "The little tufts of hair in a cat’s ear that help keep out dirt direct sounds into the ear, and insulate the ears are called \"ear furnishings.\"";
        catFacts[9] = "Approximately 40,000 people are bitten by cats in the U.S. annually.";
        catFacts[10] = "Cats do not have a collarbone, which allows them to fit through any opening the size of their head.";
        catFacts[11] = "Almost 10% of a cat's bones are in its tail, and the tail is used to maintain balance.";
        catFacts[12] = "Cats step with both left legs, then both right legs when they walk or run.";
        catFacts[13] = "The vomeronasal organ in cats can readily detect even the smallest of chemical clues in their environments, which can help them determine the proximity and status of other cats.";
        catFacts[14] = "Cats have 4 four different meanings for \"'meow'\".";
        catFacts[15] = "The Tonkinese was developed in the 1960s by crossing Siamese and Burmese cats.";
        catFacts[16] = "Cats are also known to dislike the smell of citrus and tea tree (melaleuca) oil.";
        catFacts[17] = "Certain groups of cats are more likely to have a urinary tract infection.";
        catFacts[18] = "There are two sub-species, the Pantherinae which includes the tiger, lion, jaguar, leopard, and snow leopard, and Felinae which includes the cougar, cheetah, lynxes, ocelot and the domestic cat.";
        catFacts[19] = "The technical term for a cat's hairball is a \"bezoar\".";
        catFacts[20] = "Cats will initiate contact much of the time and will remember kindness, returning the favor later.";
        catFacts[21] = "Domestic cats use many vocalizations for communication, including purring, trilling, hissing, growling/snarling, grunting, and several different forms of meowing.";
        catFacts[22] = "It is well-known that cats sleep from 14 to 18 hours a day.";
        catFacts[23] = "A 2007 Gallup poll revealed that both men and women were equally likely to own a cat.";
        catFacts[24] = "The vomeronasal organ in cats can readily detect even the smallest of chemical clues in their environments, which can help them determine the proximity and status of other cats.";
        catFacts[25] = "When a domestic cat goes after mice, about 1 pounce in 3 results in a catch.";
        catFacts[26] = "At just 2.75 inches tall and 7.5 inches long, the world's smallest cat was a Himalayan named Tinkertoy.";
        catFacts[27] = "The technical term for \"declawing\" is called an \"Onychectomy\".";
        catFacts[28] = "Cats tend to scratch to remove aged cuticles from their nails, to put a visual marker of territory, to defend themselves, and to stretch.";
        catFacts[29] = "15-42% of behavior complaints are related to cats scratching";
        catFacts[30] = "Approximately 24.4% of cats are declawed in the US";
        catFacts[31] = "It is illegal to declaw a cat in California";
        catFacts[32] = "In the UK, declawing is a last resort and can only be done when all other alternatives have been completely exhausted.";
        catFacts[33] = "Cats can become pregnant almost immediately.";
        catFacts[34] = "Some core vaccinations for cats are feline herpesvirus, feline calicivirus, feline panleukopenia, and rabies.";
        catFacts[35] = "Cats are seasonally polyestrous meaning that the females’ reproductive system is only active during breeding season during which the female cat can go through multiple estrous cycles.";
        catFacts[36] = "Cats are long day breeders meaning that when days get longer, reproductive hormones increase in activity.";
        catFacts[37] = "Indoor cats may not cycle reproductively from spring to late fall since they don’t feel the natural photoperiod and will instead cycle year-round";
        catFacts[38] = "Cats can induce ovulation.";
        catFacts[39] = "A female cat is called a \"Molly\" or \"Queen.\"";
        catFacts[40] = "A male cat is called a \"Tom.\"";
        catFacts[41] = "Cats were domesticated from African wildcat (Felis silvestris lybica).";
        catFacts[42] = "The scientific name for a cat is \"Felis catus.\"";
        catFacts[43] = "In the past, cats were used as pest control, a religious symbol of fertility and sexuality, household pets/companions, food supply protectors, ambassadors of good fortune, and sacrifices.";
        catFacts[44] = "The technical term for whiskers is \"Vibrissae.\"";
        catFacts[45] = "Cats have binocular and later vision meaning that cats can see around 280 degrees while on average humans tend to see around 100 degrees.";
        catFacts[46] = "Cats have 30 permanent teeth.";
        catFacts[47] = "Kittens have have 26 deciduous teeth.";
        catFacts[48] = "Kittens don't have molars while cats do.";
        catFacts[49] = "There are five different types of coat color palettes: deep or diluted colors, tabby patterns, piebald patterns, tortoiseshell patterns, and pointed patterns.";
        catFacts[50] = "Nearly all tortoiseshell cats are female.";
        catFacts[51] = "Kittens can't thermoregulate by themselves for the first two week of their lives.";
        catFacts[52] = "Kittens should be fed roughly every four hours or approximately five to six times a day.";
        catFacts[53] = "About 3.2 million cats are entered into U.S animal shelters nationwide every year.";
        catFacts[54] = "Roughly 860,000 cats are euthanized in shelters each year.";
        catFacts[55] = "Unreliable sources for cats are pet shops, commercial breeders, backyard breeders, and strays";
        catFacts[56] = "Cat's taste buds respond to amino acids, bitter tastes, and acids.";
        catFacts[57] = "In October 2001, the State of Maryland officially named the calico cat as its \"State Cat.\"";
        catFacts[58] = "Cats can make over 100 vocal sounds, while dogs can only make about ten.";
        catFacts[59] = "Cats can't chew large chunks of food as they can't move their jaws sideways.";
        catFacts[60] = "There are over 500 million domestic cats in the world.";
        catFacts[61] = "Cats have about 130,000 hairs per square inch (20,155 hairs per square centimeter).";
        catFacts[62] = "In 1879, Belgium came up with the idea to use cats to deliver their mail.";
        catFacts[63] = "Many cats are lactose intolerant and should not be given cow's milk.";
        catFacts[64] = "Ancient Egyptians shaved off their eyebrows to mourn the death of their cats.";
        catFacts[65] = "The Savannah cat is a crossbreed between a Serval African wild cat and domestic cats.";
        catFacts[66] = "Over 30% of households in North America own a cat.";
        catFacts[67] = "Cats breathe at a rate of 20 – 30 breaths per minute.";
        catFacts[68] = "In the United States, only about 24% of cats in shelters will be adopted.";
        catFacts[69] = "The first cat video uploaded on YouTube was filmed in 1894.";
        catFacts[70] = "Cats can have OCD.";
        catFacts[71] = "Cat urine glows under blacklights since it contains the element phosphorus.";
        catFacts[72] = "The Egyptian Mau is probably the oldest breed of cat.";
        catFacts[73] = "A cat inherited a fortune of US$13 million from its owner in Italy.";
        catFacts[74] = "Redirected aggression most often occurs when a family cat spies a neighborhood cat through a window.";
        catFacts[75] = "All orange cats are tabbies, but all tabbies are not orange.";
        catFacts[76] = "Cats tend to spend roughly 30% of their lives grooming themselves";
        catFacts[77] = "Cats communicate and navigate mostly through scent, rubbing and spraying to mark their territories.";
        catFacts[78] = "Cats need taurine, an amino acid found in other animals, to live";
        catFacts[79] = "Blue-eyed, pure white cats are frequently deaf.";
        catFacts[80] = "Cats can drink sea water.";
        catFacts[81] = "Cats are obligate carnivores and need a good source of meat protein.";
        catFacts[82] = "Cats can theoretically hear dolphins.";
        catFacts[83] = "Most female cats are right-pawed while most male cats are left-pawed.";
        catFacts[84] = "Cats seat through the pads of their paws.";
        catFacts[85] = "Cats have a third eyelid called a \"haw.\"";
        catFacts[86] = "A cat has 32 muscles which controls its outer ear and also serves as the mood barometer as the ear position suggests emotions ranging from happiness to anger.";
        catFacts[87] = "In Japan, cats are thought to have the power to turn into spirits when they die.";
        catFacts[88] = "A cat will never break a sweat because it has no sweat glands.";
        catFacts[89] = "Many cats hide when faced with an unfamiliar situation or new surroundings.";
        catFacts[90] = "Cats are especially sensitive to scented products like perfumes and colognes.";
        catFacts[91] = "Sleeping helps cats conserve energy in between their meals.";
        catFacts[92] = "Alternatives to declawing include scratching posts and/or nail caps.";
        catFacts[93] = "About 37% of American homes today have at least 1 cat.";
        catFacts[94] = "Outdoor cats tend to be farsighted and indoor cats tend to be nearsighted.";
        catFacts[95] = "Possibly because according to the Buddhist religion, the body of the cat is the resting place (temporary) of the soul of very spiritual people.";
        catFacts[96] = "A cat can be pregnant for around 58 to 65 days.";
        catFacts[97] = "Cats hate the water because their fur does not insulate well when it's wet.";
        catFacts[98] = "Cats purr at the same frequency as an idling diesel engine, about 26 cycles per second.";
        catFacts[99] = "Like birds, cats have a homing ability that uses its biological clock, the angle of the sun, and the Earth's magnetic field.";

        final TextView text = (TextView) findViewById(R.id.textView) ;
        final String tex = (String) text.getText();
        Random generator = new Random();
        int i = generator.nextInt(100);
        text.setText(catFacts[i]);
        setCurrent(i);

        Button home = (Button) findViewById(R.id.button4);
        home.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(FactActivity.this, MainActivity.class);
                startActivity(myIntent);
            }
        });

        Button factButton = (Button) findViewById(R.id.button7);
        factButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Random generator = new Random();
                int i = generator.nextInt(100);
                while (i == getCurrent()) {
                    i = generator.nextInt(100);
                }
                text.setText(catFacts[i]);
                setCurrent(i);
            }
        });

    }
    public void setCurrent(int a) {
        current = a;
    }
    public int getCurrent() {
        return current;
    }
}
