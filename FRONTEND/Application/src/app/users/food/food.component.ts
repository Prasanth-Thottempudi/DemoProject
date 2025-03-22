import { Component, HostListener, OnDestroy } from '@angular/core';
import { CommonServicesService } from 'src/app/services/common-services.service';

@Component({
  selector: 'app-food',
  templateUrl: './food.component.html',
  styleUrls: ['./food.component.css'],
})
export class FoodComponent implements OnDestroy {
  recipeList;

  constructor(private http: CommonServicesService) {}
  

  ngOnInit(): void {
    this.http.getAllRecipes().subscribe(
      (response) => {
        this.recipeList = response.reciepeList;
      },
      (error) => {
        alert('error' + error);
      }
    );
  }

  addToCart(recipe: any) {
    recipe.isAddedToCart = true;
    if (typeof recipe.quantity !== 'number') {
      recipe.quantity = 1;
    }
    console.log(
      `Recipe ID: ${recipe.reciepeId}, Quantity: ${recipe.quantity} added to cart.`
    );
  }

  incrementQuantity(recipe: any) {
    recipe.quantity += 1;
    console.log(
      `Recipe ID: ${recipe.reciepeId}, Quantity: ${recipe.quantity} incremented.`
    );
  }

  decrementQuantity(recipe: any) {
    if (recipe.quantity == 1) {
      recipe.quantity = 0;
      recipe.isAddedToCart = false;
      console.log(
        `Recipe ID: ${recipe.reciepeId}, Quantity: ${recipe.quantity} incremented.`
      );
    }
    if (recipe.quantity > 1) {
      recipe.quantity -= 1;
      console.log(
        `Recipe ID: ${recipe.reciepeId}, Quantity: ${recipe.quantity} incremented.`
      );
    }
  }

  ngOnDestroy(): void {

    alert('do u want to save the data ')
  }
}
