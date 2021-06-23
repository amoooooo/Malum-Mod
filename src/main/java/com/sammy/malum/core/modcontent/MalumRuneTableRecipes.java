package com.sammy.malum.core.modcontent;

import com.sammy.malum.core.init.items.MalumItems;
import com.sammy.malum.core.systems.recipes.ItemIngredient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.common.Tags;

import java.util.ArrayList;

public class MalumRuneTableRecipes
{
    public static final ArrayList<MalumRuneTableRecipe> RECIPES = new ArrayList<>();

    public static void init()
    {
        new MalumRuneTableRecipe(new ItemIngredient(Items.BLAZE_POWDER, 1))
                .addExtraItem(new ItemIngredient(MalumItems.INFERNAL_SPIRIT.get(), 1))
                .addExtraItem(new ItemIngredient(MalumItems.BLAZING_QUARTZ.get(), 1))
                .addExtraItem(new ItemIngredient(Tags.Items.GUNPOWDER, 1));

        new MalumRuneTableRecipe(new ItemIngredient(Items.ENDER_PEARL, 1))
                .addExtraItem(new ItemIngredient(MalumItems.ELDRITCH_SPIRIT.get(), 1))
                .addExtraItem(new ItemIngredient(MalumItems.SOULSTONE.get(), 1))
                .addExtraItem(new ItemIngredient(Tags.Items.GEMS_LAPIS, 1));

        new MalumRuneTableRecipe(new ItemIngredient(Items.GHAST_TEAR, 1))
                .addExtraItem(new ItemIngredient(MalumItems.ELDRITCH_SPIRIT.get(), 1))
                .addExtraItem(new ItemIngredient(MalumItems.SOULSTONE.get(), 1))
                .addExtraItem(new ItemIngredient(MalumItems.AQUATIC_SPIRIT.get(), 2));

        new MalumRuneTableRecipe(new ItemIngredient(MalumItems.TAINTED_ROCK_RUNE.get(), 1))
                .addExtraItem(new ItemIngredient(MalumItems.SOULSTONE.get(), 1))
                .addExtraItem(new ItemIngredient(MalumItems.TAINTED_ROCK.get(), 8))
                .addExtraItem(new ItemIngredient(MalumItems.AQUATIC_SPIRIT.get(), 2));

        new MalumRuneTableRecipe(new ItemIngredient(MalumItems.TWISTED_ROCK_RUNE.get(), 1))
                .addExtraItem(new ItemIngredient(MalumItems.SOULSTONE.get(), 1))
                .addExtraItem(new ItemIngredient(MalumItems.TWISTED_ROCK.get(), 8))
                .addExtraItem(new ItemIngredient(MalumItems.AQUATIC_SPIRIT.get(), 2));

        new MalumRuneTableRecipe(new ItemIngredient(MalumItems.HALLOWED_GOLD_INGOT.get(), 1))
                .addExtraItem(new ItemIngredient(MalumItems.SACRED_SPIRIT.get(), 2))
                .addExtraItem(new ItemIngredient(Tags.Items.INGOTS_GOLD, 1));

        new MalumRuneTableRecipe(new ItemIngredient(MalumItems.SOUL_STAINED_STEEL_INGOT.get(), 1))
                .addExtraItem(new ItemIngredient(MalumItems.WICKED_SPIRIT.get(), 1))
                .addExtraItem(new ItemIngredient(Tags.Items.INGOTS_IRON, 1));

        new MalumRuneTableRecipe(new ItemIngredient(MalumItems.RADIANT_SOULSTONE.get(), 1))
                .addExtraItem(new ItemIngredient(Tags.Items.GEMS_DIAMOND, 1))
                .addExtraItem(new ItemIngredient(MalumItems.SOULSTONE.get(), 2))
                .addExtraItem(new ItemIngredient(MalumItems.INFERNAL_SPIRIT.get(), 4));

        new MalumRuneTableRecipe(new ItemIngredient(MalumItems.CURSED_NEBULOUS.get(), 1))
                .addExtraItem(new ItemIngredient(MalumItems.ELDRITCH_SPIRIT.get(), 4))
                .addExtraItem(new ItemIngredient(Tags.Items.NETHER_STARS, 1))
                .addExtraItem(new ItemIngredient(MalumItems.RADIANT_SOULSTONE.get(), 2));
    }

    public static MalumRuneTableRecipe getRecipe(ArrayList<ItemStack> stacks)
    {
        for (MalumRuneTableRecipe recipe : RECIPES)
        {
            if (recipe.matches(stacks))
            {
                return recipe;
            }
        }
        return null;
    }


    public static class MalumRuneTableRecipe
    {
        public ArrayList<ItemIngredient> itemIngredients;
        public ItemIngredient outputIngredient;

        public MalumRuneTableRecipe(ItemIngredient outputIngredient)
        {
            this.outputIngredient = outputIngredient;
            this.itemIngredients = new ArrayList<>();
            RECIPES.add(this);
        }

        public MalumRuneTableRecipe addExtraItem(ItemIngredient ingredient)
        {
            itemIngredients.add(ingredient);
            return this;
        }

        public ArrayList<ItemStack> sortedStacks(ArrayList<ItemStack> stacks)
        {
            ArrayList<ItemStack> sortedStacks = new ArrayList<>();
            for (ItemIngredient ingredient : itemIngredients)
            {
                for (ItemStack stack : stacks)
                {
                    if (ingredient.matches(stack))
                    {
                        sortedStacks.add(stack);
                        break;
                    }
                }
            }
            return sortedStacks;
        }

        public boolean matches(ArrayList<ItemStack> stacks)
        {
            ArrayList<ItemStack> sortedStacks = sortedStacks(stacks);
            if (sortedStacks.size() < itemIngredients.size())
            {
                return false;
            }
            for (int i = 0; i < itemIngredients.size(); i++)
            {
                ItemIngredient ingredient = itemIngredients.get(i);
                ItemStack stack = sortedStacks.get(i);
                if (!ingredient.matches(stack))
                {
                    return false;
                }
            }
            return true;
        }
    }
}